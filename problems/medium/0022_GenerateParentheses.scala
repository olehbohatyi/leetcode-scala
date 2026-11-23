#!/usr/bin/env -S scala shebang

import scala.collection.mutable.*

// 22. Generate Parentheses
// Difficulty: Medium
// https://leetcode.com/problems/generate-parentheses/

// Given n pairs of parentheses, write a function to generate all combinations
// of well-formed parentheses.

// Example 1:
// Input: n = 3
// Output: ["((()))","(()())","(())()","()(())","()()()"]

// Example 2:
// Input: n = 1
// Output: ["()"]

// Constraints:
// 1 <= n <= 8

def generateParenthesesWhileStack(n: Int): List[String] =
  val r = ListBuffer[String]()
  val v = Stack(("", 0, 0))

  while v.nonEmpty do
    val (s, o, c) = v.pop()
    if o == n && c == n then r += s
    else
      if o < n then v.push((s + "(", o + 1, c))
      if c < o then v.push((s + ")", o, c + 1))

  r.toList

@main def generateParentheses(): Unit =

  val data = List(
    3 -> List("((()))", "(()())", "(())()", "()(())", "()()()"),
    1 -> List("()")
  )

  val impl = List(
    generateParenthesesWhileStack
  )

  for
    generateParenthesesDef <- impl
    (input, check)         <- data
  do
    assert:
      generateParenthesesDef(input).sorted == check.sorted
