describe('template spec', () => {
  it('passes', () => {
    cy.visit('http://localhost:5173/')
  })
})

// Describe block for grouping related test
describe('Button Clickability Test', () => {
  it('Should be clickable', () => {
    // Visit the webpage containing the button
    cy.visit('http://localhost:5173/');
    cy.get('button').should('be.visible').click();
  });
});
