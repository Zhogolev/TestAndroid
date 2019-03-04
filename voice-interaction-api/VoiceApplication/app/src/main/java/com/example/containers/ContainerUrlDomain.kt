package com.example.containers

import com.example.data.MatchingDomainUrl
import org.json.JSONArray
import org.json.JSONObject

class ContainerUrlDomain {
    private val items = arrayListOf<MatchingDomainUrl>()

    companion object {
        val instance = ContainerUrlDomain()
    }

    fun setItem(obj: JSONObject) = instance.items.add(
        MatchingDomainUrl(
            obj.getString("domain"),
            obj.getString("deeplink")
        )
    )

    fun setItems(array: JSONArray) = {
        instance.items.clear()
        for (index in 0..array.length()) setItem(array.get(index) as JSONObject)
    }
}