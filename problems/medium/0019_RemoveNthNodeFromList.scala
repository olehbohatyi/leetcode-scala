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

def removeNthNodeFromListImpl(head: ListNode, n: Int): ListNode =
  val dummy = ListNode(0, head)
  var fast  = dummy
  var slow  = dummy

  var steps = 0
  while steps < n do
    fast = fast.next
    steps += 1

  while fast.next != null do
    fast = fast.next
    slow = slow.next

  slow.next = slow.next.next
  dummy.next

@main def removeNthNodeFromList(): Unit =

  val data = List(
    (ListNode(7, ListNode(3, ListNode(9, null))), 2) -> ListNode(7, ListNode(9, null)),
    (ListNode(4, null), 1)                           -> null,
    (ListNode(5, ListNode(8, null)), 2)              -> ListNode(8, null),
    (ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5, ListNode(6, null)))))), 4) -> ListNode(
      1,
      ListNode(2, ListNode(4, ListNode(5, ListNode(6, null))))
    ),
  )

  val impl = List(
    removeNthNodeFromListImpl
  )

  for
    removeNthNodeFromListDef <- impl
    ((head -> n), check)     <- data
  do
    assert:
      removeNthNodeFromListDef(head, n) == check
