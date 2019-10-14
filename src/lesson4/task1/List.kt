@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var k = 0.0
    for (element in v) {
        k += element * element
    }
    return sqrt(k)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double =
    if (list.sum() == 0.0) 0.0
    else list.sum() / list.size

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val s = mean(list)
    for (i in 0 until list.size) {
        list[i] = list[i] - s
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in a.indices) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var n = 0
    var y = 1
    var k = 0
    var z = 0
    for (i in p.indices) {
        z = p[i]
        while (i > k) {
            y *= x
            k += 1
        }
        y *= z
        n += y
        k = 0
        y = 1
    }
    return n
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.sum() == 0) return list
    var s = list.first()
    for (i in 1 until list.size) {
        s += list[i]
        list[i] = s
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    var a = n
    for (i in 2..n) {
        while (a % i == 0) {
            a /= i
            result.add(i)
        }
    }
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): MutableList<Int> {
    val result = mutableListOf<Int>()
    var x = n
    while (x > base) {
        result.add(0, x % base)
        x /= base
    }
    result.add(0, x % base)
    return result
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val t = mutableListOf<Int>()
    val w = mutableListOf<String>()
    var x = n
    while (x > base) {
        t.add(0, x % base)
        when {
            x % base == 0 -> w.add(0, "0")
            x % base == 1 -> w.add(0, "1")
            x % base == 2 -> w.add(0, "2")
            x % base == 3 -> w.add(0, "3")
            x % base == 4 -> w.add(0, "4")
            x % base == 5 -> w.add(0, "5")
            x % base == 6 -> w.add(0, "6")
            x % base == 7 -> w.add(0, "7")
            x % base == 8 -> w.add(0, "8")
            x % base == 9 -> w.add(0, "9")
            x % base == 10 -> w.add(0, "a")
            x % base == 11 -> w.add(0, "b")
            x % base == 12 -> w.add(0, "c")
            x % base == 13 -> w.add(0, "d")
            x % base == 14 -> w.add(0, "e")
            x % base == 15 -> w.add(0, "f")
            x % base == 16 -> w.add(0, "g")
            x % base == 17 -> w.add(0, "h")
            x % base == 18 -> w.add(0, "i")
            x % base == 19 -> w.add(0, "j")
            x % base == 20 -> w.add(0, "k")
            x % base == 21 -> w.add(0, "l")
            x % base == 22 -> w.add(0, "m")
            x % base == 23 -> w.add(0, "n")
            x % base == 24 -> w.add(0, "o")
            x % base == 25 -> w.add(0, "p")
            x % base == 26 -> w.add(0, "q")
            x % base == 27 -> w.add(0, "r")
            x % base == 28 -> w.add(0, "s")
            x % base == 29 -> w.add(0, "t")
            x % base == 30 -> w.add(0, "u")
            x % base == 31 -> w.add(0, "v")
            x % base == 32 -> w.add(0, "w")
            x % base == 33 -> w.add(0, "x")
            x % base == 34 -> w.add(0, "y")
            x % base == 35 -> w.add(0, "z")
        }
        x /= base
    }
    when {
        x % base == 0 -> w.add(0, "0")
        x % base == 1 -> w.add(0, "1")
        x % base == 2 -> w.add(0, "2")
        x % base == 3 -> w.add(0, "3")
        x % base == 4 -> w.add(0, "4")
        x % base == 5 -> w.add(0, "5")
        x % base == 6 -> w.add(0, "6")
        x % base == 7 -> w.add(0, "7")
        x % base == 8 -> w.add(0, "8")
        x % base == 9 -> w.add(0, "9")
        x % base == 10 -> w.add(0, "a")
        x % base == 11 -> w.add(0, "b")
        x % base == 12 -> w.add(0, "c")
        x % base == 13 -> w.add(0, "d")
        x % base == 14 -> w.add(0, "e")
        x % base == 15 -> w.add(0, "f")
        x % base == 16 -> w.add(0, "g")
        x % base == 17 -> w.add(0, "h")
        x % base == 18 -> w.add(0, "i")
        x % base == 19 -> w.add(0, "j")
        x % base == 20 -> w.add(0, "k")
        x % base == 21 -> w.add(0, "l")
        x % base == 22 -> w.add(0, "m")
        x % base == 23 -> w.add(0, "n")
        x % base == 24 -> w.add(0, "o")
        x % base == 25 -> w.add(0, "p")
        x % base == 26 -> w.add(0, "q")
        x % base == 27 -> w.add(0, "r")
        x % base == 28 -> w.add(0, "s")
        x % base == 29 -> w.add(0, "t")
        x % base == 30 -> w.add(0, "u")
        x % base == 31 -> w.add(0, "v")
        x % base == 32 -> w.add(0, "w")
        x % base == 33 -> w.add(0, "x")
        x % base == 34 -> w.add(0, "y")
        x % base == 35 -> w.add(0, "z")
    }
    return w.joinToString(separator = "")
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    val s = digits.size - 1
    var k = 0
    var n = 0
    var d = 1
    for (i in digits.indices) {
        k = s - i
        while (k != 0) {
            k -= 1
            d *= base
        }
        n += d * digits[i]
        d = 1
    }
    return n
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val s = str.length - 1
    var n = 0
    var k = 0
    var d = 1
    var x = 0
    for (i in 0..str.length - 1) {
        when (str[i]) {
            '0' -> n = 0
            '1' -> n = 1
            '2' -> n = 2
            '3' -> n = 3
            '4' -> n = 4
            '5' -> n = 5
            '6' -> n = 6
            '7' -> n = 7
            '8' -> n = 8
            '9' -> n = 9
            'a' -> n = 10
            'b' -> n = 11
            'c' -> n = 12
            'd' -> n = 13
            'e' -> n = 14
            'f' -> n = 15
            'g' -> n = 16
            'h' -> n = 17
            'i' -> n = 18
            'j' -> n = 19
            'k' -> n = 20
            'l' -> n = 21
            'm' -> n = 22
            'n' -> n = 23
            'o' -> n = 24
            'p' -> n = 25
            'q' -> n = 26
            'r' -> n = 27
            's' -> n = 28
            't' -> n = 29
            'u' -> n = 30
            'v' -> n = 31
            'w' -> n = 32
            'x' -> n = 33
            'y' -> n = 34
            'z' -> n = 35
        }
        k = s - i
        while (k != 0) {
            k -= 1
            d *= base
        }
        x += d * n
        d = 1
    }
    return x
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val r = mutableListOf<String>()
    var p = n
    var h = n
    var o = 0
    var s = 0
    var j = "I"
    var v = "V"
    var x = "X"
    var k = 0
    if (n == 0) return "0"
    while (h != 0) {
        h /= 10
        s += 1
    }
    for (i in 1..s) {
        o = p % 10
        p /= 10
        k += 1
        when (o) {
            1 -> r.add(0, j)
            2 -> {
                r.add(0, j)
                r.add(0, j)
            }
            3 -> {
                r.add(0, j)
                r.add(0, j)
                r.add(0, j)
            }
            4 -> {
                r.add(0, v)
                r.add(0, j)
            }
            5 -> r.add(0, v)
            6 -> {
                r.add(0, j)
                r.add(0, v)
            }
            7 -> {
                r.add(0, j)
                r.add(0, j)
                r.add(0, v)
            }
            8 -> {
                r.add(0, j)
                r.add(0, j)
                r.add(0, j)
                r.add(0, v)
            }
            9 -> {
                r.add(0, x)
                r.add(0, j)
            }
        }
        when (v) {
            "V" -> {
                j = x
                v = "L"
                x = "C"
            }
            "L" -> {
                j = x
                v = "D"
                x = "M"
            }
            "D" -> {
                j = x
                v = "V"
                x = "X"
            }
        }
    }
    return r.joinToString(separator = "")
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String = TODO()
