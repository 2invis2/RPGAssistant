package com.inviz.validator

class StringsValidator {

    fun isNotBlankOrEmpty(text: String): Boolean {
    return text.trim().isNotEmpty()
    }
}