import React, { useEffect, useState } from "react";
import axios from "axios";

const API_BASE = "http://localhost:8080/api/properties";

function PropertyList() {
  const [properties, setProperties] = useState([]);
  const [error, setError] = useState(null);
  const [search, setSearch] = useState("");
  const [page, setPage] = useState(0);         // Current page
  const [totalPages, setTotalPages] = useState(0); // Total pages

  // Fetch properties with pagination + search
  const fetchProperties = async () => {
    try {
      const url =
        search.trim() === ""
          ? `${API_BASE}?page=${page}&size=3`
          : `${API_BASE}/search?keyword=${search}&page=${page}&size=3`;

      const response = await axios.get(url);

      setProperties(response.data.data.content);
      setTotalPages(response.data.data.totalPages);
      setError(null);
    } catch (err) {
      setError("Failed to fetch properties");
      setProperties([]);
    }
  };

  useEffect(() => {
    fetchProperties();
  }, [page, search]);

  // Handle search input change
  const handleSearchChange = (e) => {
    setSearch(e.target.value);
    setPage(0); // Reset to first page on search
  };

  return (
    <div style={{ width: "100%", padding: "20px" }}>
      <input
        type="text"
        placeholder="Search by city, type, title..."
        value={search}
        onChange={handleSearchChange}
        style={{
          width: "80%",
          padding: "12px",
          marginBottom: "30px",
          marginLeft: "10%",
          borderRadius: "6px",
          border: "1px solid #ccc",
        }}
      />

      <h2 style={{ fontSize: "26px", marginLeft: "20px" }}>All Properties</h2>

      {error && (
        <p style={{ color: "red", marginLeft: "20px" }}>{error}</p>
      )}

      {/* Property cards container */}
      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(3, 1fr)",
          gap: "25px",
          padding: "20px",
        }}
      >
        {properties.map((property) => (
          <div
            key={property.id}
            style={{
              border: "1px solid #eee",
              borderRadius: "10px",
              padding: "20px",
              background: "white",
              boxShadow: "0px 2px 8px rgba(0,0,0,0.1)",
            }}
          >
            <h3>{property.title}</h3>

            <p><strong>City:</strong> {property.city}</p>
            <p><strong>Price:</strong> ₹{property.price}</p>
            <p><strong>Type:</strong> {property.type}</p>
            <p><strong>Description:</strong> {property.description}</p>
            <p><strong>Location:</strong> {property.location}</p>
          </div>
        ))}
      </div>

      {/* Pagination Buttons */}
      <div
        style={{
          textAlign: "center",
          marginTop: "20px",
          marginBottom: "40px",
        }}
      >
        <button
          disabled={page === 0}
          onClick={() => setPage(page - 1)}
          style={{
            padding: "10px 15px",
            marginRight: "10px",
            background: page === 0 ? "#ccc" : "#007bff",
            color: "white",
            border: "none",
            borderRadius: "5px",
            cursor: page === 0 ? "not-allowed" : "pointer",
          }}
        >
          Previous
        </button>

        {/* Page numbers */}
        {Array.from({ length: totalPages }, (_, i) => (
          <button
            key={i}
            onClick={() => setPage(i)}
            style={{
              padding: "10px 15px",
              marginRight: "8px",
              background: page === i ? "black" : "#e0e0e0",
              color: page === i ? "white" : "black",
              border: "none",
              borderRadius: "5px",
              cursor: "pointer",
            }}
          >
            {i + 1}
          </button>
        ))}

        <button
          disabled={page === totalPages - 1}
          onClick={() => setPage(page + 1)}
          style={{
            padding: "10px 15px",
            background:
              page === totalPages - 1 ? "#ccc" : "#007bff",
            color: "white",
            border: "none",
            borderRadius: "5px",
            cursor:
              page === totalPages - 1 ? "not-allowed" : "pointer",
          }}
        >
          Next
        </button>
      </div>
    </div>
  );
}

export default PropertyList;




