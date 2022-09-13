package com.example.nowinandroid_clone.testutil

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestDispatcherRule(
    private val dispatcher: CoroutineDispatcher =
        StandardTestDispatcher()
) : TestRule {
    override fun apply(base: Statement, description: Description): Statement =
        object : Statement() {
            override fun evaluate() {
                Dispatchers.setMain(dispatcher)
                try {
                    base.evaluate()
                } finally {
                    Dispatchers.resetMain()
                }
            }
        }
}