#!/usr/bin/env -S scala shebang

// 19. Remove Nth Node From List
// Difficulty: Medium
// https://leetcode.com/problems/remove-nth-node-from-list/

// Given the head of a singly linked list and an integer n, remove the node
// that is n positions from the end, then return the possibly updated head.

// Example 1:
// Input: head = [7,3,9], n = 2
// Output: [7,9]
// Explanation: The second node from the end is removed.

// Example 2:
// Input: head = [4], n = 1
// Output: []

// Example 3:
// Input: head = [5,8], n = 2
// Output: [8]

// Constraints:
// - 1 <= number of nodes <= 30
// - 0 <= Node.val <= 100
// - 1 <= n <= number of nodes
// Follow-up: Can you solve it with one traversal?

// TODO: one function per approach, named removeNthNodeFromList<Impl>. e.g. `removeNthNodeFromListTailrec` or `removeNthNodeFromListLoop`.
def removeNthNodeFromListImpl(input: ???): ??? = ???

@main def removeNthNodeFromList(): Unit =

  val data = List(
    // input -> check
    ??? -> ???
  )

  val impl = List(
    removeNthNodeFromListImpl
  )

  for
    removeNthNodeFromListDef <- impl
    (input, check)           <- data
  do
    assert:
      removeNthNodeFromListDef(input) == check
