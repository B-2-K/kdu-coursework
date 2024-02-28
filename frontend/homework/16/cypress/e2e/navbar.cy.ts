describe('Navbar', () => {
    beforeEach(() => {
      cy.visit('http://localhost:5173/');
    });
  
    it('should update search term when input value changes', () => {
      const searchTerm = 'Search Term';
  
      cy.get('.navbar input').type(searchTerm).should('have.value', searchTerm);
    });
  });