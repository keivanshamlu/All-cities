package com.shamlou.bases_android.recyclerview.adapter
import androidx.recyclerview.widget.RecyclerView

/**
 * scrolls a recyclerview to top
 */
fun RecyclerView.scrollToTop(){
    scrollToPosition(0)
}

/**
 * groups all callbacks related to a change in recyclerview items
 */
fun RecyclerView.Adapter<*>.onItemsChanged(callBack : () -> Unit){
    registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            callBack.invoke()
        }
        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            callBack.invoke()
        }
        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            callBack.invoke()
        }
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            callBack.invoke()
        }
        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            callBack.invoke()
        }
        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            callBack.invoke()
        }
    })
}