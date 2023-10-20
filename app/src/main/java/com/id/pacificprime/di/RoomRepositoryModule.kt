package com.id.pacificprime.di

import com.id.pacificprime.room.data.RoomRepository
import com.id.pacificprime.room.domain.RoomRepositoryContract
import org.koin.dsl.module

val roomRepositoryCoreModule = module {
    single<RoomRepositoryContract> {
        RoomRepository(
            roomDao = get<PacificPrimeDatabase>().roomDao()
        )
    }
}
