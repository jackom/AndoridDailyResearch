package com.example.demotest.designpattern.builder

/**
 * @date：2020-02-20 11:30
 * @desc：
 * @author：jackom
 */

fun main() {
    val builder = ConstructorArg.Builder()
    builder.setRef(true).setArg(ConstructorArg.REF_BEAN_ID).setType(String.javaClass)
}