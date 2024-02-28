describe('TodoApp', () => {
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

    it('should delete todo when delete button is clicked', () => {
        const todoText = 'Test todo item';
    
        // Add a todo
        cy.get('.todo-input input').type(todoText).should('have.value', todoText);
        cy.get('.todo-input button#btn').click();
    
        // Check if todo is added
        cy.get('.todo-list li').should('have.length', 1);
        cy.get('.todo-list li').should('contain', todoText);
    
        // Delete the todo
        cy.get('.todo-list li button').click();
    
        // Check if todo is deleted
        cy.get('.todo-list li').should('not.exist');
      });
  
    it('should not add todo when input contains only whitespace characters', () => {
        const todoText = '\t\n';
      
        cy.get<HTMLInputElement>('.todo-input input').type(todoText).then($input => {
          const trimmedValue = $input.val()?.trim();
          if (trimmedValue !== undefined) {
            expect(trimmedValue).to.be.empty;
          }
        });
      
        cy.get('.todo-input button#btn').click();
      
        cy.get('.todo-list li').should('not.exist');
      }); 
  });