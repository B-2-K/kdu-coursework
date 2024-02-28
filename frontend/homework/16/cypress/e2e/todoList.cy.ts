  
  describe('TodoList', () => {
    beforeEach(() => {
      cy.visit('http://localhost:5173/');
    });
  
    it('should filter todos based on search term', () => {
      const todoText1 = 'Test todo item 1';
      const todoText2 = 'Another test todo item';
  
      cy.get('.todo-input input').type(todoText1).should('have.value', todoText1);
      cy.get('.todo-input button#btn').click();
      cy.get('.todo-input input').type(todoText2).should('have.value', todoText2);
      cy.get('.todo-input button#btn').click();
  
      cy.get('.navbar input').type('Another');
  
      cy.get('.todo-list li').should('have.length', 1);
      cy.get('.todo-list li').should('contain', todoText2);
    });
  });