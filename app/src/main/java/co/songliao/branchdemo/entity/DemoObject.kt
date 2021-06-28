package co.songliao.branchdemo.entity

import com.google.gson.annotations.SerializedName

/**
 ** created by Liao Song on 6/28/2021
 */
data class DemoObject(@SerializedName("\$og_title") val title:String?,
                      @SerializedName("\$og_description") val subtitle: String?,
                      @SerializedName("\$og_image_url") val imageUrl :String?)
