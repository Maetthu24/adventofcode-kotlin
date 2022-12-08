package year2022

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/year2022", "$name.txt")
    .readLines()

fun readText(name: String) = File("src/year2022", "$name.txt")
    .readText()

/**
 * Converts string to `2022`.md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')