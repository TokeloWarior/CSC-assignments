def swap ( values, source_index, dest_index ):
   """Exchange source and dest values in list."""
   values[source_index], values[dest_index] = values[dest_index], values[source_index]

def partition ( values, start_index, stop_index ):
   """Partition list in-place based on last value as pivot."""
   pivot = values[stop_index] #start with pivot at end
   midpoint_index = start_index
   for position in range (start_index, stop_index):
      global comparisons
      comparisons += 1
      if values[position] <= pivot:
         swap (values, position, midpoint_index)
         midpoint_index += 1
   swap (values, midpoint_index, stop_index) #move pivot to middle
   return midpoint_index

def quick_sort2 ( values, start_index, stop_index):
   """Sort values from start to stop using 
   quicksort algorithm."""
   if stop_index > start_index:
      pivot_index = partition (values, start_index, stop_index)
      quick_sort2 (values, start_index, pivot_index-1)
      quick_sort2 (values, pivot_index+1, stop_index)

def quick_sort ( values ):
   """Sort values using quicksort algorithm."""
   quick_sort2 (values, 0, len(values)-1)
   return values

comparisons = 0
# print(quick_sort([2,1,0,9,3,15,7]))
print(comparisons)
comparisons = 0
# print(quick_sort([34,17,12,34,14,48,23]))
print(comparisons)
comparisons = 0
print(quick_sort([1,2,3,4,5]))
print(comparisons)
comparisons = 0
print(quick_sort([5,1,3,2,4]))
print(comparisons)
