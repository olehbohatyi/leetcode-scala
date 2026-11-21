#!/usr/bin/env -S scala shebang

import scala.annotation.tailrec

// 21. Merge Two Sorted Lists
// Difficulty: Easy
// https://leetcode.com/problems/merge-two-sorted-lists/

// You are given the heads of two sorted linked lists list1 and list2.
// Merge the two lists into one sorted list. The list should be made by splicing
// together the nodes of the first two lists.
// Return the head of the merged linked list.

// Example 1:
// Input: list1 = [1,2,4], list2 = [1,3,4]
// Output: [1,1,2,3,4,4]

// Example 2:
// Input: list1 = [], list2 = []
// Output: []

// Example 3:
// Input: list1 = [], list2 = [0]
// Output: [0]

// Constraints:
// The number of nodes in both lists is in the range [0, 50].
// -100 <= Node.val <= 100
// Both list1 and list2 are sorted in non-decreasing order.

/** Definition for singly-linked list. class ListNode(_x: Int = 0, _next: ListNode = null) { var next: ListNode = _next
  * var x: Int = _x }
  */
class ListNode(_x: Int = 0, _next: ListNode = null):
  var next: ListNode = _next
  var x: Int         = _x

  override def toString: String = s"ListNode($x${Option(next).map(next => s", ${next.toString}").getOrElse("")})"

  override def equals(obj: Any): Boolean = obj match
    case that: ListNode => this.x == that.x && this.next == that.next
    case _              => false

  override def hashCode(): Int =
    import scala.util.hashing.MurmurHash3
    val nextHash = if next == null then 0 else next.hashCode()
    MurmurHash3.mix(x.hashCode(), nextHash)

def mergeTwoSortedListsRecursive(list1: ListNode, list2: ListNode): ListNode =
  if list1 == null then list2
  else if list2 == null then list1
  else if list1.x < list2.x then
    list1.next = mergeTwoSortedListsRecursive(list1.next, list2)
    list1
  else
    list2.next = mergeTwoSortedListsRecursive(list2.next, list1)
    list2

def mergeTwoSortedListsTailrec(list1: ListNode, list2: ListNode): ListNode =
  @tailrec
  def loop(one: ListNode, two: ListNode, acc: ListNode): Unit =
    one -> two match
      case (null, two)                 => acc.next = two
      case (one, null)                 => acc.next = one
      case (one, two) if one.x > two.x =>
        acc.next = two
        loop(one, two.next, acc.next)
      case (one, two) =>
        acc.next = one
        loop(one.next, two, acc.next)
  val res = ListNode(0, null)
  loop(list1, list2, res)
  res.next

def mergeTwoSortedListsWhile(list1: ListNode, list2: ListNode): ListNode =
  val head = ListNode(0, null)
  var tail = head
  var one  = list1
  var two  = list2

  while one != null && two != null do
    if one.x <= two.x then
      tail.next = one
      one = one.next
    else
      tail.next = two
      two = two.next
    tail = tail.next

  tail.next = if one != null then one else two
  head.next

@main def mergeTwoSortedLists(): Unit =

  val data = List(
    (ListNode(1, ListNode(2, ListNode(4, null))), ListNode(1, ListNode(3, ListNode(4, null)))) ->
      ListNode(1, ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(4, null)))))),
    (null, null)              -> null,
    (null, ListNode(0, null)) -> ListNode(0, null)
  )

  val impl = List(
    mergeTwoSortedListsRecursive,
    mergeTwoSortedListsTailrec,
    mergeTwoSortedListsWhile
  )

  for
    mergeTwoSortedListsDef  <- impl
    ((list1, list2), check) <- data
  do
    assert:
      mergeTwoSortedListsDef(list1, list2) == check
