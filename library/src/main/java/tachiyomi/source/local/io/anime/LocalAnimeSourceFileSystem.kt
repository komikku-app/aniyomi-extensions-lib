package tachiyomi.source.local.io.anime

import com.hippo.unifile.UniFile

abstract class LocalAnimeSourceFileSystem {

    protected abstract fun getBaseDirectory(): UniFile?

    protected abstract fun getFilesInBaseDirectory(): List<UniFile>

    protected abstract fun getAnimeDirectory(name: String): UniFile?

    protected abstract fun getFilesInAnimeDirectory(name: String): List<UniFile>
}
