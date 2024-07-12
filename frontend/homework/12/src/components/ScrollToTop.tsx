import React, { useRef } from "react";
import { para, scrollToTop } from "../styles/ScrollToTop.styles";

function ScrollToTop() {
  const scrollToTopRef = useRef<HTMLDivElement>(null);

  const scroll = () => {
    if (scrollToTopRef.current) {
      scrollToTopRef.current.scrollTo({ top: 0, behavior: "smooth" });
    }
  };

  return (
    <div ref={scrollToTopRef} style={scrollToTop}>
      <p style={para}>
        CSS (Cascading Style Sheets) is a style sheet language used for describing the presentation of a document written in a markup language like HTML. CSS describes how elements should be rendered on screen, on paper, in speech, or on other media. It controls the layout, colors, fonts, and other visual aspects of a webpage, allowing designers to separate content from presentation and maintain consistency across multiple pages and devices.
        <br />
        CSS provides a wide range of features to enhance the visual presentation of web pages, including selectors, the box model, Flexbox and Grid layouts, transitions and animations, and media queries. These features enable developers to create responsive, interactive, and visually appealing websites that adapt seamlessly to different devices and screen sizes.
        <br />
        CSS (Cascading Style Sheets) is a style sheet language used for describing the presentation of a document written in a markup language like HTML. CSS describes how elements should be rendered on screen, on paper, in speech, or on other media. It controls the layout, colors, fonts, and other visual aspects of a webpage, allowing designers to separate content from presentation and maintain consistency across multiple pages and devices.
        <br />
        CSS provides a wide range of features to enhance the visual presentation of web pages, including selectors, the box model, Flexbox and Grid layouts, transitions and animations, and media queries. These features enable developers to create responsive, interactive, and visually appealing websites that adapt seamlessly to different devices and screen sizes.
        <br />
        CSS (Cascading Style Sheets) is a style sheet language used for describing the presentation of a document written in a markup language like HTML. CSS describes how elements should be rendered on screen, on paper, in speech, or on other media. It controls the layout, colors, fonts, and other visual aspects of a webpage, allowing designers to separate content from presentation and maintain consistency across multiple pages and devices.
        <br />
        CSS provides a wide range of features to enhance the visual presentation of web pages, including selectors, the box model, Flexbox and Grid layouts, transitions and animations, and media queries. These features enable developers to create responsive, interactive, and visually appealing websites that adapt seamlessly to different devices and screen sizes.
        <br />

      </p>
      <button onClick={scroll}>Scroll To Top</button>
    </div>
  );
}

export default ScrollToTop;
