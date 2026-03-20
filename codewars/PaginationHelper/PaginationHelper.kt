class PaginationHelper<T>(val collection: List<T>, val itemsPerPage: Int) {

    val itemCount: Int
        get() = collection.size

    val pageCount: Int
        get() = Math.ceil(collection.size.toDouble() / itemsPerPage).toInt()

    fun pageItemCount(pageIndex: Int): Int {
        if (pageIndex < 0 || pageIndex >= pageCount) return -1

        val isLastPage = pageIndex == pageCount - 1
        return if (isLastPage) {
            val remainder = collection.size % itemsPerPage
            if (remainder == 0) itemsPerPage else remainder
        } else {
            itemsPerPage
        }
    }

    fun pageIndex(itemIndex: Int): Int {
        if (itemIndex < 0 || itemIndex >= collection.size) return -1
        return itemIndex / itemsPerPage
    }
}