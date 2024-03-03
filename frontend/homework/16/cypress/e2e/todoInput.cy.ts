describe('TodoInput', () => {
    beforeEach(() => {
      cy.visit('http://localhost:5173/');
    });
  
    it('should add todo when input is filled and submit button is clicked', () => {
      const todoText = 'Test todo item';
  
      cy.get('.todo-input input').type(todoText).should('have.value', todoText);
  
      cy.get('.todo-input button#btn').click();
  
      cy.get('.todo-list li').should('have.length', 1);
      cy.get('.todo-list li').should('contain', todoText);
    });
  
    it('should not add todo when input is empty and submit button is clicked', () => {
      cy.get('.todo-input input').should('have.value', '');
  
      cy.get('.todo-input button#btn').click();
  
      cy.get('.todo-list li').should('not.exist');
    });
  
    it('should trim whitespace from input before adding todo', () => {
      const todoText = '   Test todo item   ';
      const trimmedTodoText = todoText.trim();
  
      cy.get('.todo-input input').type(todoText).should('have.value', todoText);
  
      cy.get('.todo-input button#btn').click();
  
      cy.get('.todo-list li').should('have.length', 1);
      cy.get('.todo-list li').should('contain', trimmedTodoText);
    });
  
    it('should clear input after adding todo', () => {
      const todoText = 'Test todo item';
  
      cy.get('.todo-input input').type(todoText).should('have.value', todoText);
  
      cy.get('.todo-input button#btn').click();
  
      cy.get('.todo-input input').should('have.value', '');
    });
  });
  