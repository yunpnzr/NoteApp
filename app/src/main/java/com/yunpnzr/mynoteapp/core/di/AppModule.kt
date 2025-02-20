package com.yunpnzr.mynoteapp.core.di

import android.content.SharedPreferences
import com.yunpnzr.mynoteapp.core.data.datasource.LocationDataSource
import com.yunpnzr.mynoteapp.core.data.datasource.LocationDataSourceImpl
import com.yunpnzr.mynoteapp.core.data.repository.LocationRepository
import com.yunpnzr.mynoteapp.core.data.repository.LocationRepositoryImpl
import com.yunpnzr.mynoteapp.core.data.usecase.LocationUseCase
import com.yunpnzr.mynoteapp.core.data.usecase.LocationUseCaseImpl
import com.yunpnzr.mynoteapp.core.source.local.db.NoteDatabase
import com.yunpnzr.mynoteapp.core.source.local.pref.LocationPreference
import com.yunpnzr.mynoteapp.core.source.local.pref.LocationPreferenceImpl
import com.yunpnzr.mynoteapp.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    private val localModule = module {
        single {
            NoteDatabase.getDatabase(androidContext())
        }
        single {
            get<NoteDatabase>().noteDao()
        }

        single<SharedPreferences> {
            androidContext().getSharedPreferences(LocationPreferenceImpl.PREF_NAME, android.content.Context.MODE_PRIVATE)
        }
        single<LocationPreference> {
            LocationPreferenceImpl(get())
        }
    }

    private val  dataSourceModule = module {
        single<LocationDataSource> {
            LocationDataSourceImpl(get())
        }
    }

    private val repositoryModule = module {
        single<LocationRepository> {
            LocationRepositoryImpl(get())
        }
    }

    private val useCaseModule = module {
        single<LocationUseCase> {
            LocationUseCaseImpl(get(), get())
        }
    }

    private val viewModelModule = module {
        //viewModelOf(::MainViewModel)
        viewModel {
            MainViewModel(get())
        }
    }

    val module: List<Module> = listOf(
        localModule,
        dataSourceModule,
        repositoryModule,
        useCaseModule,
        viewModelModule
    )

}