#!/usr/bin/env -S scala shebang

// 23. Merge K Sorted Lists
// Difficulty: Hard
// https://leetcode.com/problems/merge-k-sorted-lists/

// You are given an array of k linked-lists lists, each linked-list is sorted
// in ascending order.
// Merge all the linked-lists into one sorted linked-list and return it.

// Example 1:
// Input: lists = [[1,4,5],[1,3,4],[2,6]]
// Output: [1,1,2,3,4,4,5,6]
// Explanation: The linked-lists are:
// [
//   1 -> 4 -> 5,
//   1 -> 3 -> 4,
//   2 -> 6
// ]
// merging them into one sorted linked list:
// 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6

// Example 2:
// Input: lists = []
// Output: []

// Example 3:
// Input: lists = [[]]
// Output: []

// Constraints:
// k == lists.length
// 0 <= k <= 10^4
// 0 <= lists[i].length <= 500
// -10^4 <= lists[i][j] <= 10^4
// lists[i] is sorted in ascending order.
// The sum of lists[i].length will not exceed 10^4.

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

def mergeKSortedListsImpl(lists: List[ListNode]): ListNode = ???

@main def mergeKSortedLists(): Unit =

  val data = List(
    List(
      ListNode(1, ListNode(4, ListNode(5, null))),
      ListNode(1, ListNode(3, ListNode(4, null))),
      ListNode(2, ListNode(6, null))
    ) -> ListNode(
      1,
      ListNode(
        1,
        ListNode(
          2,
          ListNode(3, ListNode(4, ListNode(4, ListNode(5, ListNode(6, null)))))
        )
      )
    ),
    List.empty[ListNode] -> null,
    List(null)           -> null
  )

  val impl = List(
    mergeKSortedListsImpl
  )

  for
    mergeKSortedListsDef <- impl
    (input, check)       <- data
  do
    assert:
      mergeKSortedListsDef(input) == check
