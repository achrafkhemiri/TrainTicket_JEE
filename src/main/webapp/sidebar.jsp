<head>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
</head>

<div class="sidebar">
  <div class="sidebar-header">JEE</div>
  <ul class="sidebar-menu">
    <li><a href="${pageContext.request.contextPath}/GareController"><i class="fas fa-train"></i><span>Gares</span></a></li>
    <li><a href="${pageContext.request.contextPath}/addTrain"><i class="fas fa-subway"></i><span>Trains</span></a></li>
 
  </ul>
</div>

<style>
:root {
  --sidebar-bg: #1f2d3d;      
  --sidebar-hover: #294866;  
  --sidebar-accent: #3498db;  
  --text-light: #ecf0f1;
  --text-muted: #bdc3c7;
}

body {
  margin: 0;
  padding: 0;
  font-family: 'Segoe UI', Tahoma, sans-serif;
}

.sidebar {
  width: 190px;
  height: 100vh;
  background-color: var(--sidebar-bg);
  color: var(--text-light);
  padding: 40px 20px;
  position: fixed;
  top: 0;
  left: 0;
  box-shadow: 2px 0 6px rgba(0, 0, 0, 0.2);
  display: flex;
  flex-direction: column;
}

.sidebar-header {
  font-size: 1.5rem;
  font-weight: 600;
  color: var(--sidebar-accent);
  margin-bottom: 40px;
  text-align: center;
  letter-spacing: 0.5px;
}

.sidebar-menu {
  list-style: none;
  padding: 0;
  margin: 0;
  flex-grow: 1;
}

.sidebar-menu li {
  margin-bottom: 18px;
}

.sidebar-menu li a {
  display: flex;
  align-items: center;
  gap: 12px;
  color: var(--text-muted);
  text-decoration: none;
  padding: 10px 14px;
  border-radius: 6px;
  font-weight: 500;
  transition: background-color 0.3s ease, color 0.3s ease;
}

.sidebar-menu li a:hover {
  background-color: var(--sidebar-hover);
  color: var(--text-light);
}

.sidebar-menu li a i {
  min-width: 20px;
  text-align: center;
}

.sidebar-menu li a span {
  font-size: 1em;
}
</style>
