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
def generateParenthesesImpl(n: Int): List[String] = ???

@main def generateParentheses(): Unit =

  val data = List(
    3 -> List("((()))", "(()())", "(())()", "()(())", "()()()"),
    1 -> List("()")
  )

  val impl = List(
    generateParenthesesImpl
  )

  for
    generateParenthesesDef <- impl
    (input, check)         <- data
  do
    assert:
      generateParenthesesDef(input) == check
