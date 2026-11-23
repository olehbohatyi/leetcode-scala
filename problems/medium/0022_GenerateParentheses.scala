#!/usr/bin/env -S scala shebang

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

// TODO: one function per approach, named generateParentheses<Impl>. e.g. `generateParenthesesTailrec` or `generateParenthesesLoop`.
def generateParenthesesTest(n: Int): List[String] =
  if n == 1 then List("()")
  else
    var o = n - 1
    var c = n
    val r = scala.collection.mutable.ListBuffer[String]()
    val s = scala.collection.mutable.StringBuilder("(")
    while o != 0 || c != 0 do
      if o < c then
        s += ')'
        c -= 1
      else
        s += '('
        o -= 1
    val str = s.result
    r += str
    r.result

@main def generateParentheses(): Unit =

  val data = List(
    3 -> List("((()))", "(()())", "(())()", "()(())", "()()()"),
    1 -> List("()")
  )

  val impl = List(
    generateParenthesesTest
  )

  for
    generateParenthesesDef <- impl
    (input, check)         <- data
  do
    assert:
      // generateParenthesesDef(input) == check
      println(generateParenthesesDef(input))
      true
