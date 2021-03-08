package lesson12.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag

class OpenHashSetTest {

    @Test
    @Tag("6")
    fun size() {
        val set = OpenHashSet<Int>(32)
        assertEquals(32, set.capacity)
        assertEquals(0, set.size)
        set.add(42)
        assertEquals(1, set.size)
        set.add(42)
        assertEquals(1, set.size)
        set.add(21)
        assertEquals(2, set.size)
        set.add(35)
        set.add(8)
        set.add(3)
        assertEquals(5, set.size)
    }

    @Test
    @Tag("4")
    fun isEmpty() {
        val set = OpenHashSet<Int>(16)
        assertTrue(set.isEmpty())
        set.add(0)
        assertFalse(set.isEmpty())
        set.add(0)
        assertFalse(set.isEmpty())
    }

    @Test
    @Tag("10")
    fun add() {
        val set = OpenHashSet<String>(4)
        assertTrue(set.add("alpha"))
        assertTrue(set.add("beta"))
        assertFalse(set.add("alpha"))
        assertTrue(set.add("gamma"))
        assertTrue(set.add("omega"))
        for (word in listOf("alpha", "beta", "gamma", "omega")) {
            assertTrue(word in set.elements)
        }
        assertFalse(set.add("???"))
    }

    @Test
    @Tag("10")
    fun contains() {
        val set = OpenHashSet<Int>(8)
        set.add(1)
        set.add(3)
        set.add(6)
        set.add(6)
        assertTrue(6 in set)
        assertTrue(3 in set)
        assertFalse(4 in set)
    }

    @Test
    @Tag("8")
    fun testEquals() {
        val set1 = OpenHashSet<Int>(8)
        set1.add(1)
        set1.add(3)
        set1.add(6)
        val set2 = OpenHashSet<Int>(4)
        set2.add(1)
        set2.add(3)
        set2.add(6)
        val set3 = OpenHashSet<Int>(10)
        set3.add(1)
        set3.add(3)
        set3.add(6)
        set3.add(10)
        val set4 = OpenHashSet<Int>(4)
        set4.add(1)
        set4.add(5)
        set4.add(6)
        assertTrue(set1 == set2)
        assertFalse(set1 == set3)
        set1.add(10)
        assertFalse(set1 == set2)
        assertTrue(set1 == set3)
        assertFalse(set4 == set2)
        assertFalse(set1 == set4)
    }

    @Test
    @Tag("6")
    fun testHashCode() {
        val set1 = OpenHashSet<Int>(8)
        set1.add(1)
        set1.add(3)
        set1.add(6)
        val set2 = OpenHashSet<Int>(4)
        set2.add(1)
        set2.add(3)
        set2.add(6)
        val set3 = OpenHashSet<Int>(7)
        set3.add(1)
        set3.add(3)
        set3.add(5)
        val set4 = OpenHashSet<Int>(7)
        set4.add(1)
        set4.add(3)
        set4.add(6)
        val set5 = OpenHashSet<Int>(4)
        set5.add(1)
        set5.add(9)
        set5.add(13)
        set5.add(5)
        val set6 = OpenHashSet<Int>(7)
        set6.add(1)
        set6.add(9)
        set6.add(13)
        set6.add(5)
        assertTrue(set1.hashCode() == set2.hashCode())
        assertFalse(set3.hashCode() == set4.hashCode())
        assertTrue(set3.hashCode() != set4.hashCode())
        assertTrue(set5.hashCode() == set6.hashCode())
    }
}