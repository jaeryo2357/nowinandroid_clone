package com.example.nowinandroid_clone.core.ui.component

import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import com.example.nowinandroid_clone.core.ui.icon.NiaIcons

@Composable
fun NiaFilterChip(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit
) {
    NiaOutlinedButton(
        onClick = { onCheckedChange(!checked) },
        modifier = Modifier
            .toggleable(value = checked, enabled = enabled, role = Role.Button, onValueChange = {})
            .then(modifier),
        enabled = enabled,
        small = true,
        border = NiaButtonDefaults.outlinedButtonBorder(
            enabled = enabled,
            disabledColor = MaterialTheme.colorScheme.onBackground.copy(
                alpha = if (checked) {
                    NiaButtonDefaults.DisabledButtonContentAlpha
                } else {
                    NiaButtonDefaults.DisabledButtonContainerAlpha
                }
            )
        ),
        colors = NiaButtonDefaults.outlinedButtonColors(
            containerColor = if (checked) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                Color.Transparent
            },
            disabledContainerColor = if (checked) {
                MaterialTheme.colorScheme.onBackground.copy(
                    alpha = NiaButtonDefaults.DisabledButtonContainerAlpha
                )
            } else {
                Color.Transparent
            }
        ),
        text = text,
        leadingIcon = if (checked) {
            {
                Icon(
                    imageVector = NiaIcons.Check,
                    contentDescription = null
                )
            }
        } else {
            null
        }
    )
}