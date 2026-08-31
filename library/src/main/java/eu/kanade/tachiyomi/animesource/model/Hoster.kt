package eu.kanade.tachiyomi.animesource.model

class Hoster(
    val hosterUrl: String = "",
    val hosterName: String = "",
    val videoList: List<Video>? = null,
    val internalData: String = "",
    val lazy: Boolean = false,
) {
    // TODO(1.6): Remove after ext lib bump
    constructor(
        hosterUrl: String = "",
        hosterName: String = "",
        videoList: List<Video>? = null,
        internalData: String = "",
    ) : this(hosterUrl, hosterName, videoList, internalData, false)

    companion object {
        const val NO_HOSTER_LIST = "no_hoster_list"

        fun List<Video>.toHosterList(): List<Hoster> {
            return listOf(
                Hoster(
                    hosterUrl = "",
                    hosterName = NO_HOSTER_LIST,
                    videoList = this,
                ),
            )
        }
    }
}
