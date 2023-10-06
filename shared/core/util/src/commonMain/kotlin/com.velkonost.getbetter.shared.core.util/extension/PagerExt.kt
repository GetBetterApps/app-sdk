package com.velkonost.getbetter.shared.core.util.extension

import com.kuuurt.paging.multiplatform.Pager

expect inline fun <reified K : Any, V : Any> Pager<K, V>.loadNextPage()

expect inline fun <reified K : Any, V : Any> Pager<K, V>.loadPreviousPage()

expect inline fun <reified K : Any, V : Any> Pager<K, V>.refreshPager()
