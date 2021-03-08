@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

/**
 * Класс "хеш-таблица с открытой адресацией"
 *
 * Общая сложность задания -- сложная, общая ценность в баллах -- 20.
 * Объект класса хранит данные типа T в виде хеш-таблицы.
 * Хеш-таблица не может содержать равные по equals элементы.
 * Подробности по организации см. статью википедии "Хеш-таблица", раздел "Открытая адресация".
 * Методы: добавление элемента, проверка вхождения элемента, сравнение двух таблиц на равенство.
 * В этом задании не разрешается использовать библиотечные классы HashSet, HashMap и им подобные,
 * а также любые функции, создающие множества (mutableSetOf и пр.).
 *
 * В конструктор хеш-таблицы передаётся её вместимость (максимальное количество элементов)
 */
class OpenHashSet<T>(val capacity: Int) {
    /**
     * Массив для хранения элементов хеш-таблицы
     */
    var elements = Array<Any?>(capacity) { null }

    /**
     * Число элементов в хеш-таблице
     */
    val size: Int
        get() {
            var result = 0
            for (element in elements) {
                if (element !== null) result++
            }
            return result
        }

    /**
     * Признак пустоты
     */
    fun isEmpty(): Boolean {
        for (element in elements) {
            if (element !== null) return false
        }
        return true
    }

    /**
     * Добавление элемента.
     * Вернуть true, если элемент был успешно добавлен,
     * или false, если такой элемент уже был в таблице, или превышена вместимость таблицы.
     */
    fun add(element: T): Boolean {
        val code = element.hashCode() % capacity
        if (elements[code] == element) {
            return false
        }
        if (elements[code] == null) {
            elements[code] = element
            return true
        } else {
            var i = code + 1
            var counter = i % capacity
            while (counter != code) {
                if (elements[counter] == element) {
                    return false
                }
                if (elements[counter] == null) {
                    elements[counter] = element
                    return true
                }
                i++
                counter = i % capacity
            }
        }
        return false
    }

    /**
     * Проверка, входит ли заданный элемент в хеш-таблицу
     */
    operator fun contains(element: T): Boolean {
        val code = element.hashCode() % capacity
        if (elements[code] == element) return true
        else {
            for (i in 1 until capacity) {
                if (elements[code] == element) return true
                if (elements[code] == null) {
                    return false
                }
            }
        }
        return false
    }

    private fun accordanceOfHashSet(hashSet: OpenHashSet<T>, anotherHashSet: OpenHashSet<*>): Boolean {
        val controlHashSet = OpenHashSet<Any?>(hashSet.capacity + anotherHashSet.capacity)
        for (i in 0 until anotherHashSet.capacity) {
            if (anotherHashSet.elements[i] != null) controlHashSet.add(anotherHashSet.elements[i])
        }
        for (i in 0 until hashSet.capacity) {
            if (hashSet.elements[i] != null) {
                if (controlHashSet.add(hashSet.elements[i])) return false
            }
        }
        return true
    }

    /**
     * Таблицы равны, если в них одинаковое количество элементов,
     * и любой элемент из второй таблицы входит также и в первую
     */
    override fun equals(other: Any?): Boolean =
        other is OpenHashSet<*> && this.size == other.size && accordanceOfHashSet(this, other)

    override fun hashCode(): Int {
        var result = 283
        for (i in 0 until capacity) {
            if (elements[i] != null) {
                result += elements[i].hashCode()
            }
        }
        return result
    }
}