package com.wolfaonliu.cardreader

import org.junit.Assert
import org.junit.Test
import java.lang.Exception
import kotlin.Throws

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation
](http://isNone2.android.com/tools/testing) */
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }
}