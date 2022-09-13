package com.example.nowinandroid_clone.ui

import android.os.Binder
import android.os.Bundle
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.autoSaver
import androidx.compose.runtime.snapshots.SnapshotMutableState
import androidx.core.os.bundleOf
import java.io.Serializable
import androidx.lifecycle.SavedStateHandle
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T : Any> SavedStateHandle.saveable(
    saver: Saver<T, out Any> = autoSaver(),
    init: () -> T,
): PropertyDelegateProvider<Any, ReadOnlyProperty<Any, T>> =
    PropertyDelegateProvider { _, property ->
        val value = saveable(
            key = property.name,
            saver = saver,
            init = init
        )

        ReadOnlyProperty { _, _ -> value }
    }

@JvmName("saveableMutableState")
fun <T: Any> SavedStateHandle.saveable(
    stateSaver: Saver<T, out Any> = autoSaver(),
    init: () -> MutableState<T>,
): PropertyDelegateProvider<Any, ReadWriteProperty<Any, T>> =
    PropertyDelegateProvider<Any, ReadWriteProperty<Any, T>> { _, property ->
        val mutableState = saveable(
            key = property.name,
            stateSaver = stateSaver,
            init = init
        )

        object : ReadWriteProperty<Any, T> {
            override fun getValue(thisRef: Any, property: KProperty<*>): T =
                mutableState.getValue(thisRef, property)

            override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
                mutableState.setValue(thisRef, property, value)
            }
        }
    }

fun <T: Any> SavedStateHandle.saveable(
    key: String,
    saver: Saver<T, out Any> = autoSaver(),
    init: () -> T
): T {
    @Suppress("UNCHECKED_CAST")
    saver as Saver<T, Any>

    val value = get<Bundle?>(key)?.get("value")?.let(saver::restore) ?: init()

    setSavedStateProvider(key) {
        bundleOf("value" to with(saver) {
            SaverScope(::canBeSavedToBundle).save(value)
        })
    }
    return value
}

fun <T> SavedStateHandle.saveable(
    key: String,
    stateSaver: Saver<T, out Any>,
    init: () -> MutableState<T>
): MutableState<T> = saveable(
    saver = mutableStateSaver(stateSaver),
    key = key,
    init = init
)

/**
 * Copied from RememberSaveable.kt
 */
@Suppress("UNCHECKED_CAST")
private fun <T> mutableStateSaver(inner: Saver<T, out Any>) = with(inner as Saver<T, Any>) {
    Saver<MutableState<T>, MutableState<Any?>>(
        save = { state ->
            require(state is SnapshotMutableState<T>) {
                "If you use a custom MutableState implementation you have to write a custom " +
                        "Saver and pass it as a saver param to saveable()"
            }
            mutableStateOf(save(state.value), state.policy as SnapshotMutationPolicy<Any?>)
        },
        restore = @Suppress("UNCHECKED_CAST") {
            require(it is SnapshotMutableState<Any?>)
            mutableStateOf(
                if (it.value != null) restore(it.value!!) else null,
                it.policy as SnapshotMutationPolicy<T?>
            ) as MutableState<T>
        }
    )
}

/**
 * Checks that [value] can be stored inside [Bundle].
 *
 * Copied from DisposableSaveableStateRegistry.android.kt
 */
private fun canBeSavedToBundle(value: Any): Boolean {
    // SnapshotMutableStateImpl is Parcelable, but we do extra checks
    if (value is SnapshotMutableState<*>) {
        if (value.policy === neverEqualPolicy<Any?>() ||
            value.policy === structuralEqualityPolicy<Any?>() ||
            value.policy === referentialEqualityPolicy<Any?>()
        ) {
            val stateValue = value.value
            return if (stateValue == null) true else canBeSavedToBundle(stateValue)
        } else {
            return false
        }
    }
    for (cl in AcceptableClasses) {
        if (cl.isInstance(value)) {
            return true
        }
    }
    return false
}

private val AcceptableClasses = arrayOf(
    Serializable::class.java,
    Parcelable::class.java,
    String::class.java,
    SparseArray::class.java,
    Binder::class.java,
    Size::class.java,
    SizeF::class.java
)