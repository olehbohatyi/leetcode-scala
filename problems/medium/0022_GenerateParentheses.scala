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

def generateParenthesesRecursive(n: Int): List[String] =
  if n == 1 then List("()")
  else
    val res = ListBuffer[String]()

    def aux(o: Int, c: Int, s: String): Unit =
      if o == 0 && c == 0 then res += s + ")"
      else
        if o > 0 then aux(o - 1, c, s + "(")
        if c >= o then aux(o, c - 1, s + ")")

    aux(n - 1, n - 1, "(")
    res.toList

@main def generateParentheses(): Unit =

  val data = List(
    3 -> List("((()))", "(()())", "(())()", "()(())", "()()()"),
    1 -> List("()")
  )

  val impl = List(
    generateParenthesesWhileStack,
    generateParenthesesRecursive
  )

  for
    generateParenthesesDef <- impl
    (input, check)         <- data
  do
    assert:
      generateParenthesesDef(input).sorted == check.sorted
