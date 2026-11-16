#!/usr/bin/env -S scala shebang

// 18. 4Sum
// Difficulty: Medium
// https://leetcode.com/problems/4sum/

// Given an array `nums` of `n` integers, return an array of all the unique
// quadruplets `[nums[a], nums[b], nums[c], nums[d]]` such that:
// `0 <= a, b, c, d < n`
// `a`, `b`, `c`, and `d` are distinct.
// `nums[a] + nums[b] + nums[c] + nums[d] == target`

// You may return the answer in any order.

// Example 1:
// Input: nums = [1,0,-1,0,-2,2], target = 0
// Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

// Example 2:
// Input: nums = [2,2,2,2,2], target = 8
// Output: [[2,2,2,2]]

// Constraints:
// - 1 <= nums.length <= 200
// - -10^9 <= nums[i] <= 10^9
// - -10^9 <= target <= 10^9

private def twoSum(sorted: Array[Int], start: Int, target: Long): List[List[Int]] =
  val counts = scala.collection.mutable.HashMap.empty[Int, Int]
  var index  = start
  while index < sorted.length do
    counts.update(sorted(index), counts.getOrElse(sorted(index), 0) + 1)
    index += 1

  val pairs = List.newBuilder[List[Int]]
  index = start
  while index < sorted.length do
    val left = sorted(index)
    if index == start || left != sorted(index - 1) then
      val right = target - left.toLong
      if right >= left && right <= Int.MaxValue && right >= Int.MinValue &&
        counts.get(right.toInt).exists(count => right != left || count > 1)
      then pairs += List(left, right.toInt)
    index += 1
  pairs.result()

def fourSumHash(nums: Array[Int], target: Int): List[List[Int]] =
  val sorted  = nums.sorted
  val results = List.newBuilder[List[Int]]
  var i       = 0

  while i <= sorted.length - 4 do
    if i == 0 || sorted(i) != sorted(i - 1) then
      var j = i + 1
      while j <= sorted.length - 3 do
        if j == i + 1 || sorted(j) != sorted(j - 1) then
          val pairTarget = target.toLong - sorted(i).toLong - sorted(j).toLong
          twoSum(sorted, j + 1, pairTarget)
            .foreach(pair => results += (sorted(i) :: sorted(j) :: pair))
        j += 1
    i += 1

  results.result()

private def nSum(nums: Array[Int], k: Int, target: Long): List[List[Int]] =
  val sorted = nums.sorted

  def search(start: Int, count: Int, remaining: Long): List[List[Int]] =
    if count < 2 || sorted.length - start < count then Nil
    else if count == 2 then twoSum(sorted, start, remaining)
    else
      val results = List.newBuilder[List[Int]]
      var i       = start

      while i <= sorted.length - count do
        if i == start || sorted(i) != sorted(i - 1) then
          search(i + 1, count - 1, remaining - sorted(i).toLong)
            .foreach(tail => results += (sorted(i) :: tail))
        i += 1

      results.result()

  search(0, k, target)

def fourSumNSum(nums: Array[Int], target: Int): List[List[Int]] =
  nSum(nums, 4, target.toLong)

def fourSumTwoPointers(nums: Array[Int], target: Int): List[List[Int]] =
  val sorted  = nums.sorted
  val results = List.newBuilder[List[Int]]
  var i       = 0

  while i <= sorted.length - 4 do
    if i == 0 || sorted(i) != sorted(i - 1) then
      var j = i + 1

      while j <= sorted.length - 3 do
        if j == i + 1 || sorted(j) != sorted(j - 1) then
          var left  = j + 1
          var right = sorted.length - 1

          while left < right do
            val sum = sorted(i).toLong + sorted(j).toLong + sorted(left).toLong + sorted(right).toLong
            if sum < target.toLong then left += 1
            else if sum > target.toLong then right -= 1
            else
              results += List(sorted(i), sorted(j), sorted(left), sorted(right))
              val leftValue  = sorted(left)
              val rightValue = sorted(right)
              while left < right && sorted(left) == leftValue do left += 1
              while left < right && sorted(right) == rightValue do right -= 1
        j += 1
    i += 1

  results.result()

@main def fourSum(): Unit =

  val data = List(
    (Array(1, 0, -1, 0, -2, 2), 0) -> List(List(-2, -1, 1, 2), List(-2, 0, 0, 2), List(-1, 0, 0, 1)),
    (Array(2, 2, 2, 2, 2), 8)      -> List(List(2, 2, 2, 2)),
    (Array(1000000000, 1000000000, 1000000000, 1000000000), -294967296) -> Nil
  )

  val impl = List(
    fourSumHash,
    fourSumNSum,
    fourSumTwoPointers
  )

  for
    problem4SumDef            <- impl
    ((nums -> target), check) <- data
  do
    assert:
      problem4SumDef(nums, target) == check
