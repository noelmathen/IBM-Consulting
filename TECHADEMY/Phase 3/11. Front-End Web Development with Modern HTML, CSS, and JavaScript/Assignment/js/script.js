// === Problem 8: JS Data Types ===
console.log("String:",   typeof "hello");
console.log("Number:",   typeof 123);
console.log("Boolean:",  typeof true);
let undef; console.log("Undefined:", typeof undef);

// === Problem 9: Arrays & Methods ===
let arr = [5,3,8];
arr.push(1,2);
arr.splice(2,1);        // remove one at index 2
console.log("Before sort:", arr);
arr.sort((a,b) => a - b);
console.log("After sort:", arr);

// === Problem 10: Element Selection ===
window.addEventListener("DOMContentLoaded", () => {
  console.log("By class .float-img:", document.getElementsByClassName("float-img"));
  console.log("By ID #background-demo:", document.getElementById("background-demo"));
  console.log("By tag <section>:", document.getElementsByTagName("section"));

  // === Problem 11: Add placeholder content on nav click ===
  document.querySelectorAll(".navbar a").forEach(link => {
    link.addEventListener("click", e => {
      // prevent full reload for demo
      e.preventDefault();
      const target = e.target.getAttribute("href").replace(".html","");
      document.querySelector("main").innerHTML =
        `<h1>Placeholder for ${target.toUpperCase()}</h1>`;
    });
  });

  // === Problem 12: Dynamic Form & Table ===
  document.getElementById("btnAdd").addEventListener("click", e => {
    e.preventDefault();
    const name = document.getElementById("txtName").value;
    const opt  = document.querySelector("input[name='opt']:checked");
    const color= document.getElementById("selColor").value;
    if (!name || !opt) { alert("Enter name & choose option"); return; }
    const table = document.getElementById("dataTable");
    const row   = table.insertRow();
    row.insertCell(0).innerText = name;
    row.insertCell(1).innerText = opt.value;
    row.insertCell(2).innerText = color;
  });
});
