package com.example.testdemos.designpattern.builder

import com.example.demotest.designpattern.builder.ConstructorArg

/**
 * @date：2020-02-20 11:30
 * @desc：
 * @author：jackom
 */

fun main() {
    val builder = ConstructorArg.Builder()
    builder.setRef(true).setArg(ConstructorArg.REF_BEAN_ID).setType(String::class.java)
}