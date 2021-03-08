package lesson11.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag

internal class DimensionalValueTest {

    private fun assertApproxEquals(expected: DimensionalValue, actual: DimensionalValue, eps: Double) {
        assertEquals(expected.dimension, actual.dimension)
        assertEquals(expected.value, actual.value, eps)
    }

    @Test
    @Tag("12")
    fun base() {
        val first = DimensionalValue(1.0, "Kg")
        assertEquals(1000.0, first.value)
        assertEquals(Dimension.GRAM, first.dimension)
        val second = DimensionalValue("200 m")
        assertEquals(200.0, second.value)
        assertEquals(Dimension.METER, second.dimension)
        val third = DimensionalValue("20 ca")
        assertEquals(0.2, third.value)
        assertEquals(Dimension.AMPER, third.dimension)
    }

    @Test
    @Tag("6")
    fun plus() {
        assertApproxEquals(DimensionalValue("2 Km"), DimensionalValue("1 Km") + DimensionalValue("1000 m"), 1e-8)
        assertApproxEquals(DimensionalValue("12 m"), DimensionalValue("10 m") + DimensionalValue("200 cm"), 1e-8)
        assertApproxEquals(DimensionalValue("500.2 a"), DimensionalValue("0.5 Ka") + DimensionalValue("200 ma"), 1e-8)
        assertThrows(IllegalArgumentException::class.java) {
            DimensionalValue("1 g") + DimensionalValue("1 m")
        }
    }

    @Test
    @Tag("4")
    operator fun unaryMinus() {
        assertApproxEquals(DimensionalValue("-2 g"), -DimensionalValue("2 g"), 1e-12)
        assertApproxEquals(DimensionalValue("-2.77 Ka"), -DimensionalValue("2.77 Ka"), 1e-12)
        assertThrows(IllegalArgumentException::class.java) {
            -DimensionalValue("1.01 Mm")
        }
    }

    @Test
    @Tag("6")
    fun minus() {
        assertApproxEquals(DimensionalValue("0 m"), DimensionalValue("1 Km") - DimensionalValue("1000 m"), 1e-10)
        assertApproxEquals(DimensionalValue("-0.98 g"), DimensionalValue("20 mg") - DimensionalValue("100 cg"), 1e-10)
        assertThrows(IllegalArgumentException::class.java) {
            DimensionalValue("1 g") - DimensionalValue("1 m")
        }
    }

    @Test
    @Tag("4")
    fun times() {
        assertApproxEquals(DimensionalValue("2 Kg"), DimensionalValue("2 g") * 1000.0, 1e-8)
        assertApproxEquals(DimensionalValue("2 Ka"), DimensionalValue("20000 a") * 0.1, 1e-8)
        assertApproxEquals(DimensionalValue("2 m"), DimensionalValue("2 cm") * 100.0, 1e-8)
        assertApproxEquals(DimensionalValue("2 cg"), DimensionalValue("2 mg") * 10.0, 1e-8)
        assertApproxEquals(DimensionalValue("8.2 a"), DimensionalValue("82 ca") * 10.0, 1e-8)
        assertApproxEquals(DimensionalValue("0 a"), DimensionalValue("82 ca") * 0.0, 1e-8)
    }

    @Test
    @Tag("6")
    fun divValue() {
        assertEquals(1.0, DimensionalValue("3 m") / DimensionalValue("3000 mm"), 1e-10)
        assertEquals(1000.0, DimensionalValue("3 Ka") / DimensionalValue("300 ca"), 1e-10)
        assertEquals(0.0001, DimensionalValue("3 mg") / DimensionalValue("30 g"), 1e-10)
        assertThrows(IllegalArgumentException::class.java) {
            DimensionalValue("1 g") / DimensionalValue("1 m")
        }
    }

    @Test
    @Tag("4")
    fun divDouble() {
        assertApproxEquals(DimensionalValue("42 mm"), DimensionalValue("42 m") / 1000.0, 1e-11)
        assertApproxEquals(DimensionalValue("42 a"), DimensionalValue("42 ma") / 0.001, 1e-11)
        assertApproxEquals(DimensionalValue("0.42 Kg"), DimensionalValue("42 g") / 0.1, 1e-11)
    }

    @Test
    @Tag("4")
    fun equals() {
        assertEquals(DimensionalValue("1 Kg"), DimensionalValue("1 Kg"))
        assertEquals(DimensionalValue("3 mm"), DimensionalValue("3 mm"))
        assertEquals(DimensionalValue("3000000 mm"), DimensionalValue("3 Km"))
        assertEquals(DimensionalValue("0.082 a"), DimensionalValue("82 ma"))
    }

    @Test
    @Tag("4")
    fun hashCodeTest() {
        assertEquals(DimensionalValue("1 Kg").hashCode(), DimensionalValue("1 Kg").hashCode())
        assertEquals(DimensionalValue("10 ca").hashCode(), DimensionalValue("0.1 a").hashCode())
    }

    @Test
    @Tag("4")
    fun compareTo() {
        assertTrue(DimensionalValue("1 Kg") < DimensionalValue("1500 g"))
        assertTrue(DimensionalValue("1 m") > DimensionalValue("900 mm"))
        assertFalse(DimensionalValue("1 m") == DimensionalValue("900 mm"))
        assertFalse(DimensionalValue("1 ca") > DimensionalValue("0.1 a"))
        assertTrue(DimensionalValue("100 Kg") > DimensionalValue("900 mg"))
    }
}