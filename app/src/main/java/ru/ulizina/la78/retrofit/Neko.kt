package ru.ulizina.la78.retrofit

import android.health.connect.datatypes.units.Percentage
import android.view.HapticFeedbackConstants
import androidx.constraintlayout.motion.widget.KeyPosition
import com.google.gson.annotations.SerializedName

data class Neko(
    val anime_name: String,
    val url: String
)

data class NekoResponse(
    val results: Array<Neko>
)


data class NekoCategories(
    val baka: NekoCategoriesCount,
    val bite: NekoCategoriesCount
)

data class NekoCategoriesCount(
    var min: String,
    var max: String
)

data class NekosList(
    val results: List<Neko>
)