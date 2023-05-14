package com.kfd.jobana.repository

import com.kfd.jobana.data.AdvertItem
import com.kfd.jobana.models.CloseRequest
import com.kfd.jobana.models.requests.AdvertRequest
import com.kfd.jobana.network.AdvertApiService
import com.kfd.jobana.network.AttachmentApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdvertRepository @Inject constructor(
    private val advertApiService: AdvertApiService,
    private val attachmentApiService: AttachmentApiService,
) : BaseRepository() {

    suspend fun getUserAdverts(resFunc: (adverts: List<AdvertItem>) -> Unit, error: (exception: String) -> Unit)  {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val userAdvertResponse = async {
                    advertApiService.getUserAdverts()
                }

                val resUserAdvertResponse = userAdvertResponse.await()

                var result = mutableListOf<AdvertItem>()
                resUserAdvertResponse.forEach {advert ->
                    val attachRes = mutableListOf<ByteArray>()
                    if (advert.attachments.isNotEmpty()) {
                        advert.attachments.forEach {
                            val byteArray = withContext(Dispatchers.IO) {
                                    attachmentApiService.getAttachment(it).bytes()
                                }
                            attachRes.add(byteArray)
                        }
                    }
                    result.add(
                        AdvertItem(
                        id = advert.id,
                        authorId = advert.authorId,
                        title = advert.title,
                        shortDescription = advert.shortDescription,
                        description = advert.description,
                        createdAt = advert.createdAt,
                        attachments = attachRes,
                        price = advert.price,
                        city = advert.city,
                        categories = advert.categories,
                        isClosed = advert.isClosed)
                    )
                }
                resFunc(result)
            }
        } catch (e: Exception) {
            e.message?.let { error(it) }

        }
    }
    // TODO потом подумать и исправить эти функции чтобы не было повторения кода
    suspend fun getAllAdverts(resFunc: (adverts: List<AdvertItem>) -> Unit, error: (exception: String) -> Unit)  {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val userAdvertResponse = async {
                    advertApiService.getAllAdverts()
                }
//TODO добавить обработку фильтрации, сортировки и пагинацию
                val resUserAdvertResponse = userAdvertResponse.await()

                var result = mutableListOf<AdvertItem>()
                resUserAdvertResponse.content.forEach {advert ->
                    val attachRes = mutableListOf<ByteArray>()
                    if (advert.attachments.isNotEmpty()) {
                        advert.attachments.forEach {
                            val byteArray = withContext(Dispatchers.IO) {
                                attachmentApiService.getAttachment(it).bytes()
                            }
                            attachRes.add(byteArray)
                        }
                    }
                    result.add(
                        AdvertItem(
                            id = advert.id,
                            authorId = advert.authorId,
                            title = advert.title,
                            shortDescription = advert.shortDescription,
                            description = advert.description,
                            createdAt = advert.createdAt,
                            attachments = attachRes,
                            price = advert.price,
                            city = advert.city,
                            categories = advert.categories,
                            isClosed = advert.isClosed)
                    )
                }
                resFunc(result)
            }
        } catch (e: Exception) {
            e.message?.let { error(it) }

        }
    }
    suspend fun createAdvert(advertRequest: AdvertRequest) = safeApiCall {
        advertApiService.createAdvert(advertRequest)
    }

    suspend fun changeAdvert(id: String, advertRequest: AdvertRequest) = safeApiCall {
        advertApiService.changeAdvert(id, advertRequest)
    }

    suspend fun closeOpenRequest(id: String, isClosed: Boolean) = safeApiCall {
        advertApiService.closeOpenAdvert(id, CloseRequest(isClosed))
    }

    suspend fun deleteAdvert(id: String) = safeApiCall {
        advertApiService.deleteAdvert(id)
    }
}