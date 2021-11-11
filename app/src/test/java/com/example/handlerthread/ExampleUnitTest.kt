package com.example.handlerthread

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.AnyOf.anyOf
import org.hamcrest.core.Is
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.hamcrest.core.IsNot.not
import org.junit.Assert.*
import org.junit.Test
import org.junit.Rule
import java.lang.reflect.Constructor
import java.util.*
import kotlin.reflect.jvm.internal.impl.util.ValueParameterCountCheck
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter
import kotlin.reflect.typeOf


/* HAMCREST CORE MAIN
    http://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/CoreMatchers.html#instanceOf(java.lang.Class)
    ALTRA DOCUMENTAZIONE UFFICIALE: https://junit.org/junit4/javadoc/4.13/org/hamcrest/core/IsInstanceOf.html
    INDICE: https://junit.org/junit4/javadoc/4.13/index-all.html
 */

/* 1 - Testare se estende una classe: isAssignableFrom
    SOLUZIONE PER ISTANCEOF!!!!! https://stackoverflow.com/questions/12404650/assert-an-object-is-a-specific-type
    Altra documentazione: https://stackoverflow.com/questions/52964049/check-that-method-returns-instance-of-corresponding-class
    Anche con metodi!!!!
    DOCUMENTAZIONE UFFICIALE: https://developer.android.com/reference/androidx/test/espresso/matcher/ViewMatchers#isAssignableFrom(java.lang.Class%3C?%20extends%20android.view.View%3E)
    VIEWMATCHERS!!!!
    https://stackoverflow.com/questions/3504870/how-to-test-if-one-java-class-extends-another-at-runtime
    Elementi vari di questa classe (molto interessante)
    https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html#isAssignableFrom(java.lang.Class)
    e' una istanza di una classe: if(obj instanceof B) { ... }    vedi il primo link
    INTERESSANTE: KOTLIN https://stackoverflow.com/questions/41553647/kotlin-isassignablefrom-and-reflection-type-checks
    CONTROLLARE UN ELEMENTO E' DI UN CERTO TIPO: https://stackoverflow.com/questions/12404650/assert-an-object-is-a-specific-type
 */

/* 2 - REFLECTION (BAD PRATICE) (better to test funcionality)
        In kotlin: https://kotlinlang.org/docs/reflection.html#function-references  (Official documentation)
        https://kotlinlang.org/docs/reflection.html#constructor-references  per costruttori pubblici
        REFLECTION CLASSEFIELDS INTERESSANTE!!! https://www.baeldung.com/java-reflection-class-fields
        https://softwareengineering.stackexchange.com/questions/193526/is-it-a-bad-habit-to-overuse-reflection
 */

/*3 - JUNIT AND SUPERCLASS
 */

/*4 - A cosa serve il nome in HandlerThread? esempi pratici

 */

/* 5 - ACCESS TO PRIVATE VARIABLES AND METHODS FOR TESTING
    problema spesso ricorrente!!!! dato che si va' a testare il valore di variabili che spesso sono private (interne)
    https://stackoverflow.com/questions/60205918/junit-test-case-for-private-method
 */

/* 6 - @VisibleForTesting annotation!!  si possono anche mettere casi alternativi
    https://developer.android.com/reference/androidx/annotation/VisibleForTesting
 */

/* 7 - Verifica che sia di un tipo oppure di un altro

 */

/*8 - Testare la funzionalita'!!!!!!!

 */

/* 9 - ISMARKEDNULLABLE()
        in Kotlin: isMarkedNullable
        https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-type/is-marked-nullable.html
        https://www.tabnine.com/code/java/methods/kotlin.reflect.KType/isMarkedNullable
 */

/* 10 - KTYPE!!!
        https://stackoverflow.com/questions/56686420/kotlin-kclass-from-ktype
 */

/*11 - ANY FOR MULTIPLE VALUES
        https://stackoverflow.com/questions/6028750/how-to-assert-an-actual-value-against-2-or-more-expected-values/17484883
 */

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testInstanceOf() {
        val uiHandler = MainActivity.UiHandler()
        var orderHandlerThread = OrderHandlerThread(uiHandler)
        assertThat(orderHandlerThread, instanceOf(HandlerThread::class.java))
    }

    @Test
    fun instanceOrderHandlerThread(){
        val uiHandler = MainActivity.UiHandler()
        var orderHandlerThread = OrderHandlerThread(uiHandler)
        assertThat(orderHandlerThread, instanceOf(OrderHandlerThread::class.java))
        OrderHandlerThread::getName
        // var handler = HandlerThread(Giuseppe)
        // println("nome: "+ costruttore.toString())
        //var name = orderHandlerThread.javaClass.superclass.getConstructor("String").isAccessible
        //println("nome "+name)
        //println("nome: "+ orderHandlerThread.javaClass.superclass.getDeclaredField(name))
        //println("nome "+ orderHandlerThread.javaClass.getDeclaredConstructor(uiHandler))
        //println("nome "+ orderHandlerThread::name)
    }

    @Test
    fun initialValuesVariable(){
        val uiHandler = MainActivity.UiHandler()
        var orderHandlerThread = OrderHandlerThread(uiHandler)
        assertNull(orderHandlerThread.handler)
        assertNull(orderHandlerThread.handler)
        assertThat(orderHandlerThread.random, instanceOf(Random::class.java))
    }

    @Test
    fun isNullable_isTrue(){
        val uiHandler = MainActivity.UiHandler()
        var orderHandlerThread = OrderHandlerThread(uiHandler)
        assertTrue(orderHandlerThread.handler is Handler?)
        assertFalse(orderHandlerThread.handler is Handler)
    }

    @Test
    fun convertCurrency_isRight(){
        val uiHandler = MainActivity.UiHandler()
        var orderHandlerThread = OrderHandlerThread(uiHandler)
        assertEquals(684.5f, orderHandlerThread.convertCurrency(10f))
        assertEquals(342.25f, orderHandlerThread.convertCurrency(5f))
        assertEquals(6845.0f, orderHandlerThread.convertCurrency(100.0f))
        assertEquals(0f, orderHandlerThread.convertCurrency(0f))
        not(assertEquals(342.25f, orderHandlerThread.convertCurrency(5f)))
        assertThat(342.25f, not(orderHandlerThread.convertCurrency(6f)))
    }

    @Test
    fun attachSideOrder_attached(){
        val uiHandler = MainActivity.UiHandler()
        var orderHandlerThread = OrderHandlerThread(uiHandler)
        var lista = mutableListOf<String>()
        for (i in 1..100) {
            var contorno = orderHandlerThread.attachSideOrder()
            assertThat( contorno, anyOf(`is`("Patatine"), `is`("Insalata"), `is`("Nachos")))
            assertNotNull(contorno)
            lista.add(contorno)
        }
        assertTrue(lista.contains("Patatine"))
        assertTrue(lista.contains("Insalata"))
        assertTrue(lista.contains("Nachos"))
    }

    @Test
    fun getHandler_returns(){
        val uiHandler = MainActivity.UiHandler()
        var orderHandlerThread = OrderHandlerThread(uiHandler)
        assertNull(orderHandlerThread.handler)
        val runnable = FoodRunnable()
        val foodOrder = FoodOrder(runnable.getRandomOrderName(),runnable.getRandomOrderPrice(),orderHandlerThread.attachSideOrder())
        orderHandlerThread.sendOrder(foodOrder)
        assertNotNull(runnable)
        assertNotNull(foodOrder)
    }
}
