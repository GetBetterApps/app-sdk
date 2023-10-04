package com.velkonost.getbetter.core.compose.extensions

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems

fun <T : Any> LazyListScope.items(
    items: LazyPagingItems<T>,
    key: ((item: T) -> Any)? = null,
    itemContent: @Composable LazyItemScope.(value: T?) -> Unit
) {
    items(
        count = items.itemCount,
        key = if (key == null) {
            null
        } else {
            { index ->
                val item = items.peek(index)
                if (item == null) {
                    GridPagingPlaceholderKey(index)
                } else {
                    key(item)
                }
            }
        }
    ) { index ->
        itemContent(items[index])
    }
}

@SuppressLint("BanParcelableUsage")
private data class GridPagingPlaceholderKey(private val index: Int) : Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(index)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @Suppress("unused")
        @JvmField
        val CREATOR: Parcelable.Creator<GridPagingPlaceholderKey> =
            object : Parcelable.Creator<GridPagingPlaceholderKey> {
                override fun createFromParcel(parcel: Parcel) =
                    GridPagingPlaceholderKey(parcel.readInt())

                override fun newArray(size: Int) = arrayOfNulls<GridPagingPlaceholderKey?>(size)
            }
    }
}
