up:
	docker compose up -d
down:
	dokcer compose down
db:
	docker exec -ti book_rental_db bash
redis:
	docker exec -ti redis_cache bash
