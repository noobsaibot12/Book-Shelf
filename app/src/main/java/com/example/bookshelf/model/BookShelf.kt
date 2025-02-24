package com.example.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class BooksResponse(
    val items: List<BookItem>
)

@Serializable
data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo? = null,
    val accessInfo: AccessInfo? = null
)

@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>? = null,
    val publisher: String? = null,
    val description: String? = null,
    val pageCount: Int? = null,
    val imageLinks: ImageLinks? = null,
)

@Serializable
data class SaleInfo (

    val retailPrice: RetailPrice? = null,
    val buyLink: String? = null

)

@Serializable
data class AccessInfo (

    val webReaderLink: String? = null

)

@Serializable
data class RetailPrice (

    val amount: Double? = null

)

@Serializable
data class ImageLinks(
    val thumbnail: String? = null
)