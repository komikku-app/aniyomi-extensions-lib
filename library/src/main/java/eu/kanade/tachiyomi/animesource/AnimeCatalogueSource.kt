package eu.kanade.tachiyomi.animesource

import eu.kanade.tachiyomi.animesource.model.AnimeFilterList
import eu.kanade.tachiyomi.animesource.model.AnimesPage
import eu.kanade.tachiyomi.animesource.model.SAnime
import rx.Observable

interface AnimeCatalogueSource : AnimeSource {

    /**
     * An ISO 639-1 compliant language code (two letters in lower case).
     */
    val lang: String

    /**
     * Whether the source has support for latest updates.
     */
    val supportsLatest: Boolean

    /**
     * Get a page with a list of anime.
     *
     * @since extensions-lib 14
     * @param page the page number to retrieve.
     */
    suspend fun getPopularAnime(page: Int): AnimesPage {
       throw Exception("Stub!")
    }

    /**
     * Get a page with a list of anime.
     *
     * @since extensions-lib 14
     * @param page the page number to retrieve.
     * @param query the search query.
     * @param filters the list of filters to apply.
     */
    suspend fun getSearchAnime(page: Int, query: String, filters: AnimeFilterList): AnimesPage {
        throw Exception("Stub!")
    }

    /**
     * Get a page with a list of latest anime updates.
     *
     * @since extensions-lib 14
     * @param page the page number to retrieve.
     */
    suspend fun getLatestUpdates(page: Int): AnimesPage {
        throw Exception("Stub!")
    }

    /**
     * Returns the list of filters for the source.
     */
    fun getFilterList(): AnimeFilterList

    @Deprecated(
        "Use the non-RxJava API instead",
        ReplaceWith("getPopularAnime"),
    )
    fun fetchPopularAnime(page: Int): Observable<AnimesPage>

    @Deprecated(
        "Use the non-RxJava API instead",
        ReplaceWith("getSearchAnime"),
    )
    fun fetchSearchAnime(page: Int, query: String, filters: AnimeFilterList): Observable<AnimesPage>

    @Deprecated(
        "Use the non-RxJava API instead",
        ReplaceWith("getLatestUpdates"),
    )
    fun fetchLatestUpdates(page: Int): Observable<AnimesPage>

    // KMK -->
    /**
     * Whether parsing related animes in anime page or extension provide custom related animes request.
     * @default false
     * @since anikku/extensions-lib 15
     */
    val supportsRelatedAnimes: Boolean get() = false

    /**
     * Extensions doesn't want to use App's [getRelatedAnimeListBySearch].
     * @default false
     * @since anikku/extensions-lib 15
     */
    val disableRelatedAnimesBySearch: Boolean get() = false

    /**
     * Disable showing any related animes.
     * @default false
     * @since anikku/extensions-lib 15
     */
    val disableRelatedAnimes: Boolean get() = false

    /**
     * Get all the available related animes for a anime.
     * Normally it's not needed to override this method.
     *
     * @since anikku/extensions-lib 15
     * @param anime the current anime to get related animes.
     * @return a list of <keyword, related animes>
     * @throws UnsupportedOperationException if a source doesn't support related animes.
     */
    override suspend fun getRelatedAnimeList(
        anime: SAnime,
        exceptionHandler: (Throwable) -> Unit,
        pushResults: suspend (relatedAnime: Pair<String, List<SAnime>>, completed: Boolean) -> Unit,
    ) {
        throw Exception("Stub!")
    }

    /**
     * Get related animes provided by extension
     *
     * @return a list of <keyword, related animes>
     * @since anikku/extensions-lib 15
     */
    suspend fun getRelatedAnimeListByExtension(
        anime: SAnime,
        pushResults: suspend (relatedAnime: Pair<String, List<SAnime>>, completed: Boolean) -> Unit,
    ) {
        throw Exception("Stub!")
    }

    /**
     * Fetch related animes for a anime from source/site.
     *
     * @since anikku/extensions-lib 15
     * @param anime the current anime to get related animes.
     * @return the related animes for the current anime.
     * @throws UnsupportedOperationException if a source doesn't support related animes.
     */
    suspend fun fetchRelatedAnimeList(anime: SAnime): List<SAnime> = throw UnsupportedOperationException("Unsupported!")

    /**
     * Slit & strip anime's title into separate searchable keywords.
     * Used for searching related animes.
     *
     * @since anikku/extensions-lib 15
     * @return List of keywords.
     */
    fun String.stripKeywordForRelatedAnimes(): List<String> {
        throw Exception("Stub!")
    }

    /**
     * Get related animes by searching for each keywords from anime's title.
     *
     * @return a list of <keyword, related animes>
     * @since anikku/extensions-lib 15
     */
    suspend fun getRelatedAnimeListBySearch(
        anime: SAnime,
        pushResults: suspend (relatedAnime: Pair<String, List<SAnime>>, completed: Boolean) -> Unit,
    ) {
        throw Exception("Stub!")
    }
    // KMK <--
}
