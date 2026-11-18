#!/usr/bin/env -S scala shebang

// 20. Valid Parentheses
// Difficulty: Easy
// https://leetcode.com/problems/valid-parentheses/

// Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
// determine if the input string is valid.

// An input string is valid if:
// - Open brackets must be closed by the same type of brackets.
// - Open brackets must be closed in the correct order.
// - Every close bracket has a corresponding open bracket of the same type.

// Example 1:
// Input: s = "()"
// Output: true

// Example 2:
// Input: s = "()[]{}"
// Output: true

// Example 3:
// Input: s = "(]"
// Output: false

// Example 4:
// Input: s = "([])"
// Output: true

// Example 5:
// Input: s = "([)]"
// Output: false

// Constraints:
// 1 <= s.length <= 10^4
// s consists of parentheses only '()[]{}'.

def validParenthesesStack(s: String): Boolean = ???

@main def validParentheses(): Unit =

  val data = List(
    "()"     -> true,
    "()[]{}" -> true,
    "(]"     -> false,
    "([])"   -> true,
    "([)]"   -> false
  )

  val impl = List(
    validParenthesesStack
  )

  for
    validParenthesesDef <- impl
    (input, check)      <- data
  do
    assert:
      validParenthesesDef(input) == check
