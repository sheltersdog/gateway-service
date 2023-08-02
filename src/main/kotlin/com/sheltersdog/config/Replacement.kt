package com.sheltersdog.config

enum class Replacement(val value: String) {
    REGEX("/(?<segment>.*)"),
    REPLACEMENT("/$\\{segment}"),
}