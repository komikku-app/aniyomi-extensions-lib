package tachiyomi.source.local.io.anime

import com.hippo.unifile.UniFile

class LocalAnimeSourceFileSystem {

    fun getBaseDirectory(): UniFile? = throw Exception("Stub!")

    fun getFilesInBaseDirectory(): List<UniFile> = throw Exception("Stub!")

    fun getAnimeDirectory(name: String): UniFile? = throw Exception("Stub!")

    fun getFilesInAnimeDirectory(name: String): List<UniFile> = throw Exception("Stub!")
}
