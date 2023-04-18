package com.inviz.domain.entity

fun getRPGSystemListValues() = buildList {
    add(RPGSystem.DND_5E.value)
    add(RPGSystem.CYBERPUNK_RED.value)
    add(RPGSystem.FATE_CORE.value)
}

fun findRPGSystemByValue(value: String): RPGSystem? {
    for (enumValue in enumValues<RPGSystem>()) {
        if (enumValue.value == value) {
            return enumValue
        }
    }
    return null
}