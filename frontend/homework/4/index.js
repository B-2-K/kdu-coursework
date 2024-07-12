document.addEventListener('DOMContentLoaded', function () {
    const todoInput = document.getElementById('todoInput');
    const addBtn = document.getElementById('addBtn');
    const todoList = document.getElementById('todoList');

    addBtn.addEventListener('click', function () {
        const todoText = todoInput.value.trim();

        if (todoText !== '') {
            const li = document.createElement('li');
            li.classList.add('todo-item');
            li.innerHTML = `
                <span>${todoText}</span>
                <button class="delete-btn">Delete</button>
            `;
            todoList.appendChild(li);
            todoInput.value = '';

            li.querySelector('.delete-btn').addEventListener('click', function () {
                li.remove();
            });
        }
    });
});