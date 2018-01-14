package com.sirius.cybird.di.model

import android.content.Context
import android.os.Environment
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule


/**
 *
 *Create By Botasky 31/12/2017
 **/
@GlideModule
class CybirdGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context?, builder: GlideBuilder?) {
        super.applyOptions(context, builder)
        //设置内存缓存 30MB
        val MAX_MEMORY_CACHE_SIZE = 1024 * 1024 * 30 //30MB
        builder?.setMemoryCache(LruResourceCache(MAX_MEMORY_CACHE_SIZE.toLong()))

        //硬盘缓存
        val MAX_DISK_CACHE_SIZE = 1024 * 1024 * 100 //100MB
        val CACHE_FILE_NAME = "cybirdImageCache"
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            val downloadDirectoryPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                    CACHE_FILE_NAME
            //路径---->sdcard/imgCache
            builder?.setDiskCache(DiskLruCacheFactory(downloadDirectoryPath, MAX_DISK_CACHE_SIZE.toLong()))
        } else {
            //路径---->/sdcard/Android/data/<application package>/cache/imgCache
            builder?.setDiskCache(ExternalPreferredCacheDiskCacheFactory(context, CACHE_FILE_NAME, MAX_DISK_CACHE_SIZE.toLong()))
        }

    }

    override fun registerComponents(context: Context?, glide: Glide?, registry: Registry?) {
        super.registerComponents(context, glide, registry)
    }

    override fun isManifestParsingEnabled(): Boolean {
        //Glide3是用AndroidMainfest做注册
        return false
    }
}