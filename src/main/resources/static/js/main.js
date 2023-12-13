const todoList = document.getElementById("wt-todo-list")
const doingList = document.getElementById("wt-doing-list")
const doneList = document.getElementById("wt-done-list")

window.onload = () => {
    todoList.ondrop = onDrop
    todoList.ondragover = onDragOver

    doingList.ondrop = onDrop
    doingList.ondragover = onDragOver

    doneList.ondrop = onDrop
    doneList.ondragover = onDragOver
}

const formModal = document.getElementById('formModal')
const showModal = document.getElementById('wt-new-project')

formModal.addEventListener('shown.bs.modal', () => {
    showModal.focus()
})

function onDragStart(event) {
    event.target.setAttribute("id", "currentDraggableElement")

    event
        .dataTransfer
        .setData('text/plain', event.target.id)
}

function onDragOver(event) {
    event.preventDefault()
}

function onDrop(event) {
    const id = event
        .dataTransfer
        .getData('text')

    const draggableElement = document.getElementById(id)
    draggableElement.removeAttribute("id")
    const dropZone = event.target

    if (dropZone.id === "wt-todo-list") {
        console.log("ToDo")
    }

    if (dropZone.id === "wt-doing-list") {
        console.log("Doing")
    }

    if (dropZone.id === "wt-done-list") {
        console.log("Done")
    }

    dropZone.appendChild(draggableElement)

    event
        .dataTransfer
        .clearData()
}