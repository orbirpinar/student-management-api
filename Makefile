up:
	docker compose up -d
down:
	dokcer compose down
db:
	docker exec -ti student_management_db bash
redis:
	docker exec -ti redis_cache bash
