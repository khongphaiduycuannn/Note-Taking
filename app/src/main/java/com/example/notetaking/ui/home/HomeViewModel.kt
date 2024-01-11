package com.example.notetaking.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notetaking.base.BaseViewModel
import com.example.notetaking.base.DataState
import com.example.notetaking.data.model.Note
import kotlinx.coroutines.delay

class HomeViewModel : BaseViewModel() {

    private val _notesList = MutableLiveData<MutableList<Note>>(mutableListOf())
    val notesList: LiveData<MutableList<Note>>
        get() = _notesList

    fun getNotesList() {
        executeTask(
            request = {
                delay(2000)
                DataState.Success(
                    mutableListOf<Note>(
                        Note(
                            title = "Lorem Ipsum",
                            content = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."
                        ),
                        Note(
                            title = "What is Lorem Ipsum?",
                            content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                        ),
                        Note(
                            title = "Why do we use it?",
                            content = "It is a long established fact that a reader will be distracted by the readable content."
                        ),
                        Note(
                            title = "Where does it come from?",
                            content = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old."
                        ),
                        Note(
                            title = "Where can I get some?",
                            content = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. "
                        ),
                    )
                )
            },
            onSuccess = { data ->
                this._notesList.value = data
            }
        )
    }
}