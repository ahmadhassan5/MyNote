package com.ahmadhassan.mynote.ui

import com.ahmadhassan.mynote.ui.note_detail.NoteDetailComponent
import com.ahmadhassan.mynote.ui.note_list.NoteListViewModelComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

/**
 * Created by Ahmad Hassan on 16/03/2023.
 */

class NoteRootComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext, KoinComponent {

    private val navigation = StackNavigation<Config>()

    private val _stack = childStack(
        source = navigation,
        initialConfiguration = Config.List,
        handleBackButton = true,
        childFactory = ::createChild
    )

    val stack: Value<ChildStack<*, Child>> = _stack

    private fun createChild(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            is Config.List -> Child.List(noteList(componentContext))
            is Config.Details -> Child.Details(noteDetail(componentContext, config))
        }
    }

    private fun noteList(componentContext: ComponentContext) =
        NoteListViewModelComponent(componentContext, noteDataSource = get()) {
            navigation.push(Config.Details(it))
        }

    private fun noteDetail(componentContext: ComponentContext, config: Config.Details) =
        NoteDetailComponent(
            componentContext, noteId = config.id, noteDataSource = get()
        ) {
            navigation.pop()
        }

    sealed class Child {
        class List(val component: NoteListViewModelComponent) : Child()
        class Details(val component: NoteDetailComponent) : Child()
    }

    private sealed class Config : Parcelable {
        @Parcelize
        object List : Config()

        @Parcelize
        data class Details(val id: Long?) : Config()
    }
}